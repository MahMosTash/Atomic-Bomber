package view.menus;

import javafx.scene.control.Label;
import javafx.scene.input.MouseEvent;
import model.App;
import model.User;
import view.Main;

import java.util.ArrayList;
import java.util.Comparator;

public class LeaderboardController {
    public Label username1;
    public Label username2;
    public Label username3;
    public Label username4;
    public Label username5;
    public Label username6;
    public Label username7;
    public Label username8;
    public Label username9;
    public Label username10;
    public Label point1;
    public Label point2;
    public Label point3;
    public Label point4;
    public Label point5;
    public Label point6;
    public Label point7;
    public Label point8;
    public Label point9;
    public Label point10;
    public ArrayList<Label> usernames = new ArrayList<>();
    public ArrayList<Label> points = new ArrayList<>();

    public void initialize(){
        addThings();
        ArrayList<User> users = App.allUsers;
        users.sort(Comparator.comparing(User::getScore).reversed());
        for (int i = 0; i < 10; i++){
            if ((i + 1) > users.size() || (i + 1) > usernames.size()){
                break;
            }
            usernames.get(i).setText(users.get(i).getUsername());
            points.get(i).setText(Integer.toString(users.get(i).getScore()));
        }
    }
    public void addThings(){
        usernames.add(username1);
        usernames.add(username2);
        usernames.add(username3);
        usernames.add(username4);
        usernames.add(username5);
        usernames.add(username6);
        usernames.add(username7);
        usernames.add(username8);
        usernames.add(username9);
        usernames.add(username10);
        points.add(point1);
        points.add(point2);
        points.add(point3);
        points.add(point4);
        points.add(point5);
        points.add(point6);
        points.add(point7);
        points.add(point8);
        points.add(point9);
        points.add(point10);
    }
    public void score(MouseEvent mouseEvent) {
        ArrayList<User> users = App.allUsers;
        users.sort(Comparator.comparing(User::getScore).reversed());
        for (int i = 0; i < 10; i++){
            if ((i + 1) > users.size() || (i + 1) > usernames.size()){
                break;
            }
            usernames.get(i).setText(users.get(i).getUsername());
            points.get(i).setText(Integer.toString(users.get(i).getScore()));
        }
    }

    public void difficulty(MouseEvent mouseEvent) {
        ArrayList<User> users = App.allUsers;
        users.sort(Comparator.comparing(User::getDifficultyScore).reversed());
        for (int i = 0; i < 10; i++){
            if ((i + 1) > users.size() || (i + 1) > usernames.size()){
                break;
            }
            usernames.get(i).setText(users.get(i).getUsername());
            points.get(i).setText(Integer.toString(users.get(i).getDifficultyScore()));
        }
    }

    public void kill(MouseEvent mouseEvent) {
        ArrayList<User> users = App.allUsers;
        users.sort(Comparator.comparing(User::getKills).reversed());
        for (int i = 0; i < 10; i++){
            if ((i + 1) > users.size() || (i + 1) > usernames.size()){
                break;
            }
            usernames.get(i).setText(users.get(i).getUsername());
            points.get(i).setText(Integer.toString(users.get(i).getKills()));
        }
    }

    public void accuracy(MouseEvent mouseEvent) {
        ArrayList<User> users = App.allUsers;
        users.sort(Comparator.comparing(User::getAccuracy).reversed());
        for (int i = 0; i < 10; i++){
            if ((i + 1) > users.size() || (i + 1) > usernames.size()){
                break;
            }
            usernames.get(i).setText(users.get(i).getUsername());
            points.get(i).setText(String.format("%.1f", users.get(i).getAccuracy()));
        }
    }

    public void back(MouseEvent mouseEvent) throws Exception {
        MainMenu mainMenu = new MainMenu();
        mainMenu.start(Main.stage);
    }
}
