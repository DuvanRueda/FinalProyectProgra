package co.edu.uptc.model;    
/*
 * Author: Duvan Steven Rueda Prieto
 * Date: 12/05/2025
 * Description: Final project of Progamacion I, about reviews for accommodations.
 */
public class Housing {
    
    private Room normalRooms[][];
    private Room vipRooms[][];
    private Room premiunRooms[][];

    public Housing(){
    }

    public boolean validateRooms(String typeRoom, int rows, int columns) {
        if(rows > 0 && columns > 0){
            initRooms(typeRoom, rows, columns);
            return true;
        }
        return false;
    }

    public void initRooms(String typeRoom, int rows, int columns) {
        if(typeRoom.equals("normales")){
            initNormalRooms(typeRoom, rows, columns);
        } else if(typeRoom.equals("VIP")){
            initVipRooms(typeRoom, rows, columns);
        } else {
            initPremiunRooms(typeRoom, rows, columns);
            
        }
    }

    public void initNormalRooms(String typeRoom,int rows, int columns){
        normalRooms = new Room[rows][columns];
        creatreRooms(typeRoom, normalRooms);
    }
    public void initVipRooms(String typeRoom,int rows, int columns){
        vipRooms = new Room[rows][rows];
        creatreRooms(typeRoom, vipRooms);
    }
    public void initPremiunRooms(String typeRoom,int rows, int columns){
        premiunRooms = new Room[rows][columns];
        creatreRooms(typeRoom, premiunRooms);
    }

    public void creatreRooms(String typeRoom,Room[][] rooms) {
        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[0].length; j++) {
                rooms[i][j] = new Room("Cabaña" + typeRoom + (i+j+1));
            }
        }
    }
}