class PlayerSong {
    static int playerCount = 0;
    private String name;
    public PlayerSong(String n) {
        name = n;
        playerCount++ ;
    }
}