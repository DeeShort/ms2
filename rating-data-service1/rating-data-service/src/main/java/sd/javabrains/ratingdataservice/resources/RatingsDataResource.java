package sd.javabrains.ratingdataservice.resources;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import sd.javabrains.ratingdataservice.rmodels.Rating;
import sd.javabrains.ratingdataservice.rmodels.UserRating;

@RestController
@RequestMapping("/ratingsdata")
public class RatingsDataResource {
	
	@RequestMapping("/{movieId}")
	public Rating getRating(@PathVariable("movieId") String movieId){
		return new Rating(movieId, 4);
	}
	
	@RequestMapping("users/{userId}")
	public UserRating getUserRating(@PathVariable("userId") String userId){
		List<Rating> ratings = Arrays.asList(
				new Rating("1234", 2),
				new Rating("5678", 4),
				new Rating("3456", 7)	
				);
		//return ratings;
		
			UserRating userRating = new UserRating();
			userRating.setUserRating(ratings);
			return userRating;
	}

}
