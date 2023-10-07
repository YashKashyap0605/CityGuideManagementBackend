package com.cityguide.cityguidemanagement;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
//import org.springframework.context.ApplicationContext;
//
//import com.cityguide.cityguidemanagement.entity.Attractions;
//import com.cityguide.cityguidemanagement.entity.Reviews;
//import com.cityguide.cityguidemanagement.entity.TypeOfAttraction;
//import com.cityguide.cityguidemanagement.entity.Users;
//import com.cityguide.cityguidemanagement.exception.AttractionAlreadyExistsException;
//import com.cityguide.cityguidemanagement.exception.AttractionNotFoundException;
//import com.cityguide.cityguidemanagement.exception.UserAlreadyExistsException;
//import com.cityguide.cityguidemanagement.exception.UserNotFoundException;
//import com.cityguide.cityguidemanagement.model.AttractionOutputModel;
//import com.cityguide.cityguidemanagement.model.ReviewOutputModel;
//import com.cityguide.cityguidemanagement.model.UserOutputModel;
//import com.cityguide.cityguidemanagement.service.AttractionsService;
//import com.cityguide.cityguidemanagement.service.ReviewsService;
//import com.cityguide.cityguidemanagement.service.TypeOfAttractionService;
//import com.cityguide.cityguidemanagement.service.UserService;

@SpringBootApplication
public class CityGuideManagementApplication {

	public static void main(String[] args) {

		SpringApplication.run(CityGuideManagementApplication.class, args);
//		UserService userService = ctx.getBean(UserService.class);
//		AttractionsService attractionService = ctx.getBean(AttractionsService.class);
//		ReviewsService reviewsService = ctx.getBean(ReviewsService.class);
//		TypeOfAttractionService typeOfAttractionService = ctx.getBean(TypeOfAttractionService.class);
//		LocalDate dt = LocalDate.parse("2018-11-01");
//		Timestamp ts = Timestamp.from(Instant.now());

//		Users user = new Users();
//		user.setId(1);
//		user.setUserName("YK");
//		user.setFirstName("Yash");
//		user.setLastName("Kashyap");
//		user.setEmail("yash@gmail.com");
//		user.setAdmin(false);
//		user.setCreatedAt(ts);
//		user.setDob(dt);
//		user.setPassword("123456");
//		try {
//			userService.addUser(user);
//		} catch (UserAlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(user);
//
//		TypeOfAttraction type = new TypeOfAttraction();
//		type.setId(1);
//		type.setName("Hotel");
//		typeOfAttractionService.addTypeOfAttraction(type);
//		System.out.println(type);
//
//		Attractions att = new Attractions();
//		att.setDistanceFromStation(12.7f);
//		att.setId(1);
//		att.setLikes(0);
//		att.setName("ITC ROYAL");
//		att.setRating(4.2f);
//		att.setReports(0);
//		att.setType(type);
//		att.setUser(user);
//		try {
//			attractionService.addAttraction(att);
//		} catch (AttractionAlreadyExistsException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(att);
//
////		Attractions att2 = new Attractions();
////		att.setDistanceFromStation(22.7f);
////		att.setId(2);
////		att.setLikes(10);
////		att.setName("ITC ROYAL TIGER");
////		att.setRating(4.3f);
////		att.setReports(0);
////		att.setType(type);
////		att.setUser(user);
////		try {
////			attractionService.addAttraction(att);
////		} catch (AttractionAlreadyExistsException e) {
////			// TODO Auto-generated catch block
////			e.printStackTrace();
////		}
////		System.out.println(att);
//
//		Reviews review = new Reviews();
//		review.setAttraction(att);
//		review.setComments("One of the best 5 star hotels ever");
//		review.setCreatedAt(ts);
//		review.setId(1);
//		review.setLikes(0);
//		review.setReports(0);
//		review.setUser(user);
//		reviewsService.addReview(review);
//		System.out.println(review);
//
//  
//
//		try {
//			UserOutputModel  userByEmail = userService.getUserByEmail("yash@gmail.com");
//			System.out.println(userByEmail.getUserName());
//		} catch (UserNotFoundException e) {
//			System.out.println("Exception");
//		}
//
//		AttractionOutputModel allNames = null;
//		try {
//			allNames = attractionService.getAttractionByName("ITC ROYAL");
//		} catch (AttractionNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(allNames);
//
//		List<AttractionOutputModel> search = new ArrayList<>();
//
//		try {
//			search = attractionService.searchByKeyword("ITC");
//		} catch (AttractionNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//
//		for (int i = 0; i < search.size(); i++) {
//			System.out.println(search.get(i).getName());
//		}
//
////		
//
//		List<AttractionOutputModel> attractionsByUser = new ArrayList<>();
//
//		try {
//			attractionsByUser = attractionService.findAllAttractionsByUser(user.getId());
//		} catch (AttractionNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(attractionsByUser.get(0).getName());
//
//		List<ReviewOutputModel> reviewsByAttraction = new ArrayList<>();
//
//		try {
//			reviewsByAttraction = reviewsService.findAllReviewsByAttraction(att.getId());
//		} catch (AttractionNotFoundException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		System.out.println(reviewsByAttraction.get(0).getComments());

	}

}
