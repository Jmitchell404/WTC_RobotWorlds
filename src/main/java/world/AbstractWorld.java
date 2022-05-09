package world;

//import Communication.ObjectData;
//import Robot.IRobot;
//import World.Maze.IMaze;
//import World.Maze.RandomMaze;
//import World.Mines.*;
//import World.Obstacles.IObstacle;
//import World.Pits.*;
//import server.ServerConfig;
//import server.SimpleServer;
//
//import java.util.*;
//
//public abstract class AbstractWorld implements World{
//
//    public static Position TOP_LEFT;
//    public static Position BOTTOM_RIGHT;
//    public static int visibility;
//    public static int reload ;
//    public static int repair;
//    public static int mine;
//    public ServerConfig config;
//
//    private final ArrayList<SimpleServer> clients;
//    private final IMaze maze;
//    private final Set<IMine> mines = new HashSet<>();
//
//    public AbstractWorld(ArrayList<SimpleServer> clients, ServerConfig config) {
//        this.clients = clients;
//        maze = new RandomMaze(config);
//        visibility = config.getVisibility();
//        reload = config.getReload();
//        repair = config.getRepair();
//        mine = config.getMine();
//        TOP_LEFT = new Position((-config.getWidth() /2), (config.getHeight() /2));
//        BOTTOM_RIGHT = new Position((config.getWidth() /2), (-config.getHeight() /2));
//        this.config = config;
//    }
//
//    public List<ObjectData> getObjects(IRobot robot){
//        List<ObjectData> objects = new ArrayList<>();
//
//        for (int i = robot.getPosition().getY(); i < robot.getPosition().getY()+visibility+1; i++){
//            int distance = i-robot.getPosition().getY();
//            String type = checkObjects(robot, distance, new Position(robot.getPosition().getX(),i));
//            if (type != null){
//                ObjectData object = new ObjectData("NORTH", type, distance);
//                objects.add(object);
//                if (type.equalsIgnoreCase("OBSTACLE") || type.equalsIgnoreCase("EDGE")){ break;}
//            }
//        }
//
//        for (int i = robot.getPosition().getY(); i > robot.getPosition().getY()-visibility-1; i--){
//            int distance = robot.getPosition().getY()-i;
//            String type = checkObjects(robot, distance, new Position(robot.getPosition().getX(),i));
//            if (type != null){
//                ObjectData object = new ObjectData("SOUTH", type, distance);
//                objects.add(object);
//                if (type.equalsIgnoreCase("OBSTACLE") || type.equalsIgnoreCase("EDGE")){ break;}
//            }
//        }
//
//        for (int i = robot.getPosition().getX(); i < robot.getPosition().getX()+visibility+1; i++){
//            int distance = i-robot.getPosition().getX();
//            String type = checkObjects(robot, distance, new Position(i, robot.getPosition().getY()));
//            if (type != null){
//                ObjectData object = new ObjectData("EAST", type, distance);
//                objects.add(object);
//                if (type.equalsIgnoreCase("OBSTACLE") || type.equalsIgnoreCase("EDGE")){ break;}
//            }
//        }
//
//        for (int i = robot.getPosition().getX(); i > robot.getPosition().getX()-visibility-1; i--){
//            int distance = robot.getPosition().getX()-i;
//            String type = checkObjects(robot, distance, new Position(i, robot.getPosition().getY()));
//            if (type != null){
//                ObjectData object = new ObjectData("WEST", type, distance);
//                objects.add(object);
//                if (type.equalsIgnoreCase("OBSTACLE") || type.equalsIgnoreCase("EDGE")){ break;}
//            }
//        }
//
//        return objects;
//    }
//
//    private String checkObjects(IRobot robot, int distance, Position lookPosition){
//
//        for (IObstacle obstacle : maze.getObstacles()) {
//            if (obstacle.blocksPosition(lookPosition)){
//                return "OBSTACLE";
//            }
//        }
//        for (IPit pit : maze.getPits()) {
//            if (pit.blocksPosition(lookPosition)){
//                return "PIT";
//            }
//        }
//        for (IMine mine : mines) {
//            if (mine.blocksPosition(lookPosition) && distance <= visibility/4){
//                return "MINE";
//            }
//        }
//        for (SimpleServer server : clients) {
//            if (!server.getClientRobot().getPosition().toString().equalsIgnoreCase(robot.getPosition().toString())){
//                if (server.getClientRobot().getPosition().toString().equalsIgnoreCase(lookPosition.toString())){
//                    return "ROBOT";
//                }
//            }
//        }
//        if (!isNewPositionAllowed(lookPosition)){
//            return "EDGE";
//        }
//        return null;
//    }
//
//    public IMaze getMaze() {
//        return maze;
//    }
//
//    /**
//     * Checks if the new position will be allowed, i.e. falls within the constraints of the world.
//     * @param position the position to check
//     * @return true if it is allowed, else false
//     */
//    public boolean isNewPositionAllowed(Position position) {
//        return (position.isIn(TOP_LEFT,BOTTOM_RIGHT));
//    }
//
//    /**
//     * Checks if the robot is at one of the edges of the world
//     * @return true if the robot's current is on one of the 4 edges of the world
//     */
//    public boolean isAtEdge(IRobot robot) {
//        return (robot.getPosition().getX() == TOP_LEFT.getX() ||
//                robot.getPosition().getX() == BOTTOM_RIGHT.getX() ||
//                robot.getPosition().getY() == TOP_LEFT.getY() ||
//                robot.getPosition().getY() == BOTTOM_RIGHT.getY());
//    }
//
//    public AbstractWorld getWorld(){
//        return this;
//    }
//
//    public void addMine(IMine mine){mines.add(mine);}
//
//    public void removeMine(Position position){
//        for (IMine mine : mines) {
//            if (mine.blocksPosition(position)){
//                mines.remove(mine);
//                return;
//            }
//        }
//    }
//
//    public Set<IMine> getMines() {
//        return mines;
//    }
//
//    public ArrayList<SimpleServer> getClients() {
//        return clients;
//    }
//
//    public ServerConfig getConfig() {
//        return config;
//    }
//    }