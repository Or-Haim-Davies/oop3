package view;

import game.tiles.Position;
import java.io.IOException;
import java.nio.file.*;
import java.util.*;
import java.util.stream.*;

public class GameRunner {
    private Position[][] levels;
    List<char[][]> mazes;
    public GameRunner(String[] args){
        setLevels(args);
    }
    public void setLevels(String[] args) {
        Path folderPath = Paths.get(args[0]);
        System.out.println("Reading mazes from: " + folderPath.toAbsolutePath());

        if (!Files.isDirectory(folderPath)) {
            System.out.println("Provided path is not a directory.");
            return;
        }

        List<char[][]> mazes = new ArrayList<>();

        try (Stream<Path> paths = Files.walk(folderPath)) {
            List<Path> mazeFiles = paths
                    .filter(Files::isRegularFile)
                    .filter(path -> path.toString().endsWith(".txt"))
                    .collect(Collectors.toList());

            for (Path file : mazeFiles) {
                List<String> lines = Files.readAllLines(file);
                if (lines.isEmpty()) continue;

                int rows = lines.size();
                int cols = lines.get(0).length();

                char[][] maze = new char[rows][cols];
                for (int i = 0; i < rows; i++) {
                    maze[i] = lines.get(i).toCharArray();
                }

                mazes.add(maze);
                System.out.println("Loaded maze from " + file.getFileName() + " (" + rows + "x" + cols + ")");
            }

        } catch (IOException e) {
            System.err.println("Error reading maze files.");
            e.printStackTrace();
        }

        System.out.println("\nTotal mazes loaded: " + mazes.size());
        this.mazes=mazes;
    }
    public void toPosition(){
            for(int i=0;i<mazes.getFirst().length;i++){
                for(int j=0;j<mazes.getFirst()[i].length;j++){

                }
            }
        }
    }
}
