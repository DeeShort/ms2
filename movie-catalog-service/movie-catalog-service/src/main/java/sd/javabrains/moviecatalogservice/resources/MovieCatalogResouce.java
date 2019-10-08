package sd.javabrains.moviecatalogservice.resources;

import static java.util.Collections.singletonList;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import sd.javabrains.moviecatalogservice.models.CatalogItem;
import sd.javabrains.moviecatalogservice.models.Movie;
import sd.javabrains.moviecatalogservice.models.Rating;
import sd.javabrains.moviecatalogservice.models.UserRating;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResouce {
	
	@Autowired
	private RestTemplate restTemplate;
	
//	@Autowired
//	private WebClient.Builder webClientBuilder;
	
	@RequestMapping("/{userId}")
	public List<CatalogItem> getCatalog(@PathVariable("userId")String userId) {
		
		
		
		UserRating ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/"+userId, UserRating.class);
				
				
//	..Removed from ratings= ...			Arrays.asList(
//				new Rating("1234", 2),
//				new Rating("5678", 4),
//				new Rating("3456", 7)				
//				);
				
				return ratings.getUserRating().stream().map(rating->{
					///For each movie ID, call movie info service and get details
				Movie movie = restTemplate.getForObject("http://localhost:8082/movies/"+rating.getMovieId(), Movie.class);					
				
//					Movie movie = webClientBuilder.build()
//							.get()
//							.uri("http://localhost:8082/movies/"+rating.getMovieId())
//							.retrieve()
//							.bodyToMono(Movie.class)
//							.block();
					
				
					//Put them all together
					return new CatalogItem(movie.getName(),"Test",rating.getRating());
				
				})
				
						
						.collect(Collectors.toList());
				
		
		// get all rated movie IDs
		/// For each Movie id call movie info service and get details
		//Put them all together
//		return Collections.singletonList(
//				new CatalogItem("Transformers","Test",4));
	}
	
}
