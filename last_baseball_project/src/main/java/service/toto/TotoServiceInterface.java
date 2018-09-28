package service.toto;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.ui.Model;

public interface TotoServiceInterface {

	String MakeToToScore() throws IOException;
	List Select_gamelist();
	String Make_game(HttpServletRequest request);
	
	String[] Game_Result(String m_id);
	
	
	
}
