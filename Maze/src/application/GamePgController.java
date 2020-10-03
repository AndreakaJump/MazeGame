package application;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.HPos;
import javafx.geometry.Pos;
import javafx.geometry.VPos;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.RowConstraints;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;

public class GamePgController implements Initializable{

    @FXML
    private StackPane mazeStackPane;
    @FXML
    private Button resetBtn;

    @FXML
    private Button rightBtn;

    @FXML
    private Button leftBtn;

    @FXML
    private Button upBtn;

    @FXML
    private Button downBtn;

    
    int gridMaxHW =450;
	int gridHeight;
	int gridWidth;
	int rows;
	int columns;
	
	GridPane gp;
	Grid grid;
	Circle c;
	Rectangle r;
	Distance distance;
	Distance distance2;
	Cell goalCell;     

	@Override
	public void initialize(URL arg0, ResourceBundle arg1) {
		/*
		FXMLLoader loader = new FXMLLoader();
		SettingPgController controller = loader.getController(); 
		rows = controller.getSlider();
		columns = rows;
		*/
		
	}
	
	public void getData(int i) {
		System.out.println("gothere");
		rows = i;
		columns= i;
		setUpGrid();
		setStartAndEnd();
	}
	
	@FXML
    void resetGrid(MouseEvent event) {
		setUpGrid();
		setStartAndEnd();	
    }

	public int getRows() {
		return rows;
	}

	public void setRows(int rows) {
		this.rows = rows;
	}

	public int getColumns() {
		return columns;
	}

	public void setColumns(int columns) {
		this.columns = columns;
	}

	private void setUpGrid() {
		System.out.println("gothere2");
		if(rows>columns) {
			gridHeight = gridMaxHW;
			gridWidth = (gridMaxHW/rows)*columns;
		} else {
			gridWidth = gridMaxHW;
			gridHeight = (gridMaxHW/rows)*columns;
		}
		grid = new Grid(rows,columns);
		AldousBroder ab = new AldousBroder(grid);
		ab.iterateGrid();
		gp = new GridPane();
		mazeStackPane.getChildren().add(gp);
		
		mazeStackPane.setMinSize(gridWidth, gridHeight);
		mazeStackPane.setMaxSize(gridWidth, gridHeight);
		
		distance = new Distance(grid);
		distance2 = new Distance(distance.getMaxCell(), grid);
		
		
		
		for (int i = 0; i < rows; i++) {
	         RowConstraints row = new RowConstraints(gridHeight/rows);
	         gp.getRowConstraints().add(row);
	     }
		for (int i = 0; i < columns; i++) {
	         ColumnConstraints column = new ColumnConstraints(gridWidth/columns);
	         gp.getColumnConstraints().add(column);
	     }

		for(int i = 0; i<rows;i++) 
		{
			for(int j = 0; j<columns; j++) {
				
				ImageView img = new ImageView();
				try {
					img.setImage(new Image(new FileInputStream(grid.getCell(i, j).whatPng())));
				} catch (FileNotFoundException e) {
					e.printStackTrace();
				}
				img.setFitWidth(gridWidth/columns);
				img.setFitHeight(gridHeight/rows);
				gp.add(img, j, i);
			}
		}
	}
	
	private void setStartAndEnd() {
		c= new Circle();
		c.setFill(Color.web("1290c3"));
		c.setRadius(((gridHeight/rows)/2)-((gridHeight/rows)/20));
		System.out.println(grid.getCurrentCell().getRow() + " " + grid.getCurrentCell().getColumn());
		gp.add(c, grid.getCurrentCell().getColumn(), grid.getCurrentCell().getRow());
		GridPane.setValignment((Node)c, VPos.CENTER);
		GridPane.setHalignment((Node)c, HPos.CENTER);
		
		c.setFocusTraversable(true);
		c.setOnKeyPressed(new EventHandler<KeyEvent>()
	    {
	        @Override
	        public void handle(KeyEvent ke)
	        {
	            if (ke.getCode().equals(KeyCode.UP))
	            {
	            	upBtn.fire();
	                System.out.println("UP");
	            } else if (ke.getCode().equals(KeyCode.DOWN))
	            {
	            	downBtn.fire();
	                System.out.println("down");
	            } else if (ke.getCode().equals(KeyCode.RIGHT))
	            {
	            	rightBtn.fire();
	                System.out.println("right");
	            } else if (ke.getCode().equals(KeyCode.LEFT))
	            {
	            	leftBtn.fire();
	                System.out.println("Left");
	            }
	        }
	        
	    });
		
		
		r = new Rectangle();
		r.setHeight((gridHeight/rows)-((gridHeight/rows)/10));
		r.setWidth((gridWidth/columns)-((gridWidth/columns)/10));
		r.setFill(Color.web("00ee00"));
		System.out.println(grid.getGoalCell().getRow() + " " + grid.getGoalCell().getColumn());
		gp.add(r, grid.getGoalCell().getColumn(), grid.getGoalCell().getRow());
		GridPane.setValignment((Node)r, VPos.CENTER);
		GridPane.setHalignment((Node)r, HPos.CENTER);
		
		StackPane.setAlignment((Node)gp, Pos.CENTER);
    	//resetBtn.setDisable(true);
	}
	
	@FXML
    void down(ActionEvent event) {
    	if(grid.moveCell("down")) {
    		gp.getChildren().remove(c);
    		gp.add(c, grid.getCurrentCell().getColumn(), grid.getCurrentCell().getRow());
    	}
    	if(grid.getCurrentCell()==grid.getGoalCell()) {
    		winningSequence();
    	}
    	
    }

    @FXML
    void left(ActionEvent event) {
    	if(grid.moveCell("left")) {
    		gp.getChildren().remove(c);
    		gp.add(c, grid.getCurrentCell().getColumn(), grid.getCurrentCell().getRow());
    	}
    	if(grid.getCurrentCell()==grid.getGoalCell()) {
    		winningSequence();
    	}
    }

    @FXML
    void right(ActionEvent event) {
    	if(grid.moveCell("right")) {
    		gp.getChildren().remove(c);
    		gp.add(c, grid.getCurrentCell().getColumn(), grid.getCurrentCell().getRow());
    	
    	}
    	if(grid.getCurrentCell()==grid.getGoalCell()) {
    		winningSequence();
    	}

    }
    
    @FXML
    void up(ActionEvent event) {
    	//System.out.println("upevent");
    	if(grid.moveCell("up")) {
    		gp.getChildren().remove(c);
    		gp.add(c, grid.getCurrentCell().getColumn(), grid.getCurrentCell().getRow());
    	}
    	if(grid.getCurrentCell()==grid.getGoalCell()) {
    		winningSequence();
    	}
    }
    
    private void winningSequence() {
		System.out.println("You won!");
		//resetBtn.setDisable(false);
		
	}
    
    @FXML
    void settingClicked(MouseEvent event) {
    	Parent parent = null;
		try {
			parent = FXMLLoader.load(getClass().getResource("/application/SettingPg.fxml"));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    	Scene scene = new Scene(parent);
    	scene.getStylesheets().add(getClass().getResource("application.css").toExternalForm());
    	Stage window = (Stage)((Node)event.getSource()).getScene().getWindow();
    	window.setScene(scene);
    	window.show();

    }


}