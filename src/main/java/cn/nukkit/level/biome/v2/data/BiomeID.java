package cn.nukkit.level.biome.v2.data;

public enum BiomeID {

    PLAINS(1, "plains"),
    DESERT(2, "desert"),
    EXTREME_HILLS(3, "extreme_hills"),
    FOREST(4, "forest"),
    TAIGA(5, "taiga"),
    
    OCEAN(42, "ocean");
    
    private int id;
    private String namespacedId;
    
    private BiomeID(int id, String namespacedId) {
        this.id = id;
        this.namespacedId = namespacedId;
    }
    
    public int getId() {
        return this.id;
    }
    
    public String getNamespacedId() {
        return this.namespacedId;
    }
}
