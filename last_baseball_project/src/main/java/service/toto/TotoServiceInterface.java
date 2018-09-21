package service.toto;

import java.io.IOException;
import java.util.List;

import org.springframework.ui.Model;

public interface TotoServiceInterface {

	String MakeToToScore() throws IOException;
	
	List Select_gamelist();
	
	
	
}
