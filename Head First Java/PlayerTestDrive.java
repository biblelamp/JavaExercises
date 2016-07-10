public class PlayerTestDrive {

	public static void main(String[] args) {
		System.out.println(PlayerSong.playerCount);
		PlayerSong one = new PlayerSong("Tiqer Woods");
		System.out.println(PlayerSong.playerCount);
	}
}