package cn.nukkit.level.generator.v2;

import cn.nukkit.blockstate.BlockState;
import cn.nukkit.level.ChunkManager;
import cn.nukkit.level.biome.v2.Biome;
import cn.nukkit.level.biome.v2.BiomeManager;
import cn.nukkit.level.biome.v2.layer.LayerManager;
import cn.nukkit.level.format.generic.BaseFullChunk;
import cn.nukkit.level.generator.noise.vanilla.f.NoiseGeneratorOctavesF;
import cn.nukkit.level.generator.noise.vanilla.f.NoiseGeneratorPerlinF;
import cn.nukkit.level.generator.populator.type.Populator;
import cn.nukkit.math.NukkitRandom;
import cn.nukkit.math.Vector3;

import com.google.common.collect.ImmutableList;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ThreadLocalRandom;

/**
 * @author GoodLucky777
 * <p>
 * This generator originally adapted from the Glowstone's OverworldGenerator (https://github.com/GlowstoneMC/Glowstone/blob/1624f6018232c87639e648eb5ad6c703b7b06745/src/main/java/net/glowstone/generator/OverworldGenerator.java)
 * 
 * Glowstone licensed under the following MIT license:
 * 
 * Glowstone Copyright (C) 2015-2020 The Glowstone Project.
 * Glowstone Copyright (C) 2011-2014 Tad Hardesty.
 * Lightstone Copyright (C) 2010-2011 Graham Edgecombe.
 * 
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 * 
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 * 
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
public class NormalV2 extends Generator {

    private static final BlockState STATE_STONE = BlockState.of(STONE);
    private static final BlockState STATE_WATER = BlockState.of(STILL_WATER);
    
    public static final int SEA_LEVEL = 63;
    
    private float coordinateScale = 684.412f;
    private float heightScale = 684.412f;
    public float roughnessNoise2Scale = 512.0f;
    private float roughnessNoiseScale = 512.0f;
    private float detailNoiseScaleX = 80.0f;
    private float detailNoiseScaleY = 160.0f;
    private float detailNoiseScaleZ = 80.0f;
    private float heightNoiseScaleX = 200.0f;
    private float heightNoiseScaleZ = 200.0f;
    private float heightNoiseScaleExponent = 0.5f;
    private float baseSize = 8.5f;
    private float stretchY = 12.0f;
    private float biomeHeightWeight = 1.0f;
    private float biomeHeightOffset = 0.0f;
    private float biomeScaleWeight = 1.0f;
    private float biomeScaleOffset = 0.0f;
    
    private ChunkManager level;
    private NukkitRandom nukkitRandom;
    
    private long localSeed1;
    private long localSeed2;
    
    private NoiseGeneratorOctavesF roughnessNoiseGen;
    private NoiseGeneratorOctavesF roughnessNoise2Gen;
    private NoiseGeneratorOctavesF detailNoiseGen;
    private NoiseGeneratorPerlinF surfaceNoiseGen;
    private NoiseGeneratorOctavesF scaleNoise;
    private NoiseGeneratorOctavesF heightNoiseGen;
    
    private final double[][][] density = new double[5][5][33];
    private float[] biomeWeights;
    
    private double[] detailNoise;
    private double[] roughnessNoise;
    private double[] roughnessNoise2;
    private double[] heightNoise;
    
    private BiomeManager biomeManager;
    private LayerManager layerManager;
    private int[] biomeData;
    
    private List<Populator> populators = Lists.newArrayList();
    private List<Populator> generationPopulators = Lists.newArrayList();
    
    public NormalV2() {
        this(Collections.emptyMap());
    }
    
    public NormalV2(Map<String, Object> options) {
        // Does Nothing
    }
    
    @Override
    public int getId() {
        return TYPE_INFINITE;
    }
    
    @Override
    public ChunkManager getChunkManager() {
        return this.level;
    }
    
    @Override
    public String getName() {
        return "normalV2";
    }
    
    @Override
    public Map<String, Object> getSettings() {
        return Collections.emptyMap();
    }
    
    @Override
    public Vector3 getSpawn() {
        return new Vector3(0.5, 256, 0.5);
    }
    
    @Override
    public void init(ChunkManager level, NukkitRandom random) {
        this.level = level;
        this.nukkitRandom = random;
        this.nukkitRandom.setSeed(this.level.getSeed());
        
        this.localSeed1 = ThreadLocalRandom.current().nextLong();
        this.localSeed2 = ThreadLocalRandom.current().nextLong();
        
        this.roughnessNoiseGen = new NoiseGeneratorOctavesF(this.nukkitRandom, 16);
        this.roughnessNoise2Gen = new NoiseGeneratorOctavesF(this.nukkitRandom, 16);
        this.detailNoiseGen = new NoiseGeneratorOctavesF(this.nukkitRandom, 8);
        this.surfaceNoiseGen = new NoiseGeneratorPerlinF(this.nukkitRandom, 4);
        this.scaleNoise = new NoiseGeneratorOctavesF(this.nukkitRandom, 10);
        this.heightNoiseGen = new NoiseGeneratorOctavesF(this.nukkitRandom, 16);
        
        this.heightMap = new double[825];
        this.biomeWeights = new float[25];
        for (int i = -2; i <= 2; i++) {
            for (int j = -2; j <= 2; j++) {
                this.biomeWeights[i + 2 + (j + 2) * 5] = 10.0F / Math.sqrt((float)(i * i + j * j) + 0.2F);
            }
        }
        
        this.biomeManager = new BiomeManager();
        this.biomeManager.init();
        
        this.layerManager = new LayerManager();
        this.layerManager.init();
    }
    
    @Override
    public void generateChunk(final int chunkX, final int chunkZ) {
        // For coordinate
        int baseX = chunkX << 4;
        int baseZ = chunkZ << 4;
        
        // × 4 scale
        int scaleX = chunkX << 2;
        int scaleZ = chunkZ << 2;
        
        this.nukkitRandom.setSeed(chunkX * this.localSeed1 ^ chunkZ * this.localSeed2 ^ this.level.getSeed());

        BaseFullChunk chunk = this.level.getChunk(chunkX, chunkZ);
        
        // Init noise
        this.heightNoise = this.heightNoiseGen.generateNoiseOctaves(this.heightNoise, scaleX, scaleZ, 5, 5, (double) this.heightNoiseScaleX, (double) this.heightNoiseScaleZ, (double) this.heightNoiseScaleExponent);
        this.detailNoise = this.detailNoiseGen.generateNoiseOctaves(this.detailNoise, scaleX, 0, scaleZ, 5, 33, 5, (double) (this.coordinateScale / this.detailNoiseScaleX), (double) (this.heightScale / this.detailNoiseScaleY), (double) (this.coordinateScale / this.detailNoiseScaleZ));
        this.roughnessNoise = this.roughnessNoiseGen.generateNoiseOctaves(this.roughnessNoise, scaleX, 0, scaleZ, 5, 33, 5, (double) this.coordinateScale, (double) this.heightScale, (double) this.coordinateScale);
        this.roughnessNoise2 = this.roughnessNoise2Gen.generateNoiseOctaves(this.roughnessNoise2, scaleX, 0, scaleZ, 5, 33, 5, (double) this.coordinateScale, (double) this.heightScale, (double) this.coordinateScale);
        
        this.biomeData = this.layerManager.generateBiomeData(scaleX - 2, scaleZ - 2, 10, 10);
        
        int index = 0;
        int indexHeight = 0;
        
        // Sampling densities.
        // Ideally we would sample 512 (4x4x32) values but in reality we need 825 values (5x5x33).
        // This is because linear interpolation is done later to re-scale so we need right and bottom edge values if we want it to be "seamless".
        for (int i = 0; i < 5; i++) {
            for (int j = 0; j < 5; j++) {
                float avgHeightScale = 0.0f;
                float avgHeightBase = 0.0f;
                float totalWeight = 0.0f;
                
                Biome biome = this.biomeManager.getBiomeFromId(this.biomeData[i + 2 + (j + 2) * 10]);
                
                for (int m = 0; m < 5; m++) {
                    for (int n = 0; n < 5; n++) {
                        Biome nearBiome = this.biomeManager.getBiomeFromId(this.biomeData[k + m + (j + n) * 10]);
                        float heightBase = this.biomeHeightOffset + nearBiome.getHeight() * this.biomeHeightWeight;
                        float heightScale = this.biomeScaleOffset + nearBiome.getHeightVariation() * this.biomeScaleWeight;
                        
                        // For amplified biome
                        /*if (this.getId() == TYPE_AMPLIFIED && heightBase > 0.0f) {
                            heightBase = 1.0f + heightBase * 2.0f;
                            heightScale = 1.0f + heightScale * 4.0f;
                        }*/
                        
                        float weight = this.biomeWeights[m + n * 5] / (heightBase + 2.0f);
                        
                        if (nearBiome.getHeight() > biome.getHeight()) {
                            weight *= 0.5f;
                        }
                        
                        avgHeightScale += heightScale * weight;
                        avgHeightBase += heightBase * weight;
                        totalWeight += weight;
                    }
                }
                
                avgHeightScale /= totalWeight;
                avgHeightBase /= totalWeight;
                avgHeightScale = avgHeightScale * 0.9f + 0.1f;
                avgHeightBase = (avgHeightBase * 4.0f - 1.0f) / 8.0f;
                
                double noiseH = this.heightNoise[indexHeight] / 8000.0d;
                if (noiseH < 0.0d) {
                    noiseH = Math.abs(noiseH) * 0.3d;
                }
                
                noiseH = noiseH * 3.0d - 2.0d;
                
                if (noiseH < 0.0d) {
                    noiseH = Math.max(noiseH * 0.5d, -1.0d) / 1.4d * 0.5d;
                } else {
                    noiseH = Math.min(noiseH, 1.0d) / 8.0d;
                }
                
                indexHeight++;
                
                double avgHeightBaseD = ((double) avgHeightBase + noiseH * 0.2d) * (double) this.baseSize / 8.0d;
                double avgHeightScaleD = (double) avgHeightScale;
                
                double noiseH2 = (double) this.baseSize + avgHeightBaseD * 4.0d;
                
                for (int k = 0; k < 33; k++) {
                    // density should be lower and lower as we climb up, this gets a height value to subtract from the noise.
                    double nh = ((double) k - noiseH2) * (double) this.stretchY * 128.0d / 256.0d / avgHeightScaleD;
                    
                    if (nh < 0.0d) {
                        nh *= 4.0d;
                    }
                    
                    double noiseR = this.roughnessNoise[index] / (double) this.roughnessNoiseScale;
                    double noiseR2 = this.roughnessNoise2[index] / (double) this.roughnessNoise2Scale;
                    double noiseD = (this.detailNoise[index] / 10.0d + 1.0d) / 2.0d;
                    // linear interpolation
                    double dens = noiseD < 0 ? noiseR : noiseD > 1 ? noiseR2 : noiseR + (noiseR2 - noiseR) * noiseD;
                    dens -= nh;
                    index++;
                    
                    if (k > 29) {
                        double lowering = (double)((float)(k - 29) / 3.0f);
                        // linear interpolation
                        dens = dens * (1.0d - lowering) + -10.0d * lowering;
                    }
                    
                    this.density[i][j][k] = dens;
                }
            }
        }
        
        // Terrain densities are sampled at different resolutions (1/4x on x,z and 1/8x on y by default) so it's needed to re-scale it. Linear interpolation is used to fill in the gaps.
        int fill = 0;
        int afill = Math.abs(fill);
        int seaFill = 0;
        double densityOffset = 0.0d;
        
        for (int i = 0; i < 5 - 1; i++) {
            for (int j = 0; j < 5 - 1; j++) {
                for (int k = 0; k < 33 - 1; k++) {
                    // 2x2 grid
                    double d1 = this.density[i][j][k];
                    double d2 = this.density[i + 1][j][k];
                    double d3 = this.density[i][j + 1][k];
                    double d4 = this.density[i + 1][j + 1][k];
                    
                    // 2x2 grid (row above)
                    double d5 = (this.density[i][j][k + 1] - d1) / 8d;
                    double d6 = (this.density[i + 1][j][k + 1] - d2) / 8d;
                    double d7 = (this.density[i][j + 1][k + 1] - d3) / 8d;
                    double d8 = (this.density[i + 1][j + 1][k + 1] - d4) / 8d;

                    for (int l = 0; l < 8; l++) {
                        double d9 = d1;
                        double d10 = d3;
                        for (int m = 0; m < 4; m++) {
                            double dens = d9;
                            for (int n = 0; n < 4; n++) {
                                // any density higher than density offset is ground, any density lower or equal to the density offset is air (or water if under the sea level).
                                // this can be flipped if the mode is negative, so lower or equal to is ground, and higher is air/water and, then data can be shifted by afill the order is air by default, ground, then water.
                                // they can shift places within each if statement the target is densityOffset + 0, since the default target is 0, so don't get too confused by the naming.
                                if (afill == 1 || afill == 10 || afill == 13 || afill == 16) {
                                    chunk.setBlockState(m + (i << 2), l + (k << 3), n + (j << 2), STATE_WATER);
                                } else if (afill == 2 || afill == 9 || afill == 12 || afill == 15) {
                                    chunk.setBlockState(m + (i << 2), l + (k << 3), n + (j << 2), STATE_STONE);
                                }
                                if (dens > densityOffset && fill > -1 || dens <= densityOffset && fill < 0) {
                                    if (afill == 0 || afill == 3 || afill == 6 || afill == 9 || afill == 12) {
                                        chunk.setBlockState(m + (i << 2), l + (k << 3), n + (j << 2), STATE_STONE);
                                    } else if (afill == 2 || afill == 7 || afill == 10 || afill == 16) {
                                        chunk.setBlockState(m + (i << 2), l + (k << 3), n + (j << 2), STATE_WATER);
                                    }
                                } else if (l + (k << 3) < SEA_LEVEL - 1 && seaFill == 0 || l + (k << 3) >= SEA_LEVEL - 1 && seaFill == 1) {
                                    if (afill == 0 || afill == 3 || afill == 7 || afill == 10 || afill == 13) {
                                        chunk.setBlockState(m + (i << 2), l + (k << 3), n + (j << 2), STATE_WATER);
                                    } else if (afill == 1 || afill == 6 || afill == 9 || afill == 15) {
                                        chunk.setBlockState(m + (i << 2), l + (k << 3), n + (j << 2), STATE_STONE);
                                    }
                                }
                                // interpolation along z
                                dens += (d10 - d9) / 4d;
                            }
                            // interpolation along x
                            d9 += (d2 - d1) / 4d;
                            // interpolate along z
                            d10 += (d4 - d3) / 4d;
                        }
                        // interpolation along y
                        d1 += d5;
                        d3 += d7;
                        d2 += d6;
                        d4 += d8;
                    }
                }
            }
        }
        
        // Set biome per (x, z)
        this.biomeData = this.layerManager.generateBiomeData(baseX, baseZ, 16, 16);
        for (int x = 0; x < 16; x++) {
            for (int z = 0; z < 16; z++) {
                chunk.setBiome(x, z, this.biomeManager.getBiomeFromId(this.biomeData[x + (z * 16)]));
            }
        }
        
        // Populate generation populator
        for (Populator populator : this.generationPopulators) {
            populator.populate(this.level, chunkX, chunkZ, this.nukkitRandom, chunk);
        }
    }

    @Override
    public void populateChunk(int chunkX, int chunkZ) {
        BaseFullChunk chunk = this.level.getChunk(chunkX, chunkZ);
        this.nukkitRandom.setSeed(0xdeadbeef ^ (chunkX << 8) ^ chunkZ ^ this.level.getSeed());
        
        for (Populator populator : this.populators) {
            populator.populate(this.level, chunkX, chunkZ, this.nukkitRandom, chunk);
        }
        
        this.biomeManager.getBiomeFromId(chunk.getBiomeId(16, 16)).populateChunk(this.level, chunkX, chunkZ, this.nukkitRandom);
    }
}
