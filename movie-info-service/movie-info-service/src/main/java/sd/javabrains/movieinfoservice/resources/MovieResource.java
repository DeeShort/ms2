package sd.javabrains.movieinfoservice.resources;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sd.javabrains.movieinfoservice.models.Movie;


@RestController
@RequestMapping("/movies")
public class MovieResource {
	
	@RequestMapping("/{movieId}")
	public Movie getmovieInfo(@PathVariable("movieId") String movieId ) {
		return new Movie(movieId,"Test name");
	}

}
