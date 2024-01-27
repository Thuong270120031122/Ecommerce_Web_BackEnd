package com.springboot.ecommercewebsite.controller;

import com.springboot.ecommercewebsite.exception.ProductException;
import com.springboot.ecommercewebsite.exception.UserException;
import com.springboot.ecommercewebsite.model.Rating;
import com.springboot.ecommercewebsite.model.User;
import com.springboot.ecommercewebsite.request.RatingRequest;
import com.springboot.ecommercewebsite.service.RatingService;
import com.springboot.ecommercewebsite.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/ratings")
public class RatingController {
    @Autowired
    private UserService userService;

    @Autowired
    private RatingService ratingService;

    @PostMapping("/create")
    public ResponseEntity<Rating> createRating(
            @RequestBody RatingRequest req,
            @RequestHeader("Authorization") String jwt
            )  throws UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);

        Rating rating = ratingService.createRating(req,user);

        return new ResponseEntity<Rating>(rating, HttpStatus.CREATED);
    }

    @GetMapping("/product/{productId}")
    public  ResponseEntity<List<Rating>> getProductsRating(
            @PathVariable Long productId,
            @RequestHeader("Authorization") String jwt
    )throws  UserException, ProductException{
        User user = userService.findUserProfileByJwt(jwt);

        List<Rating> ratings = ratingService.getProductsRating(productId);

        return new ResponseEntity<List<Rating>>(ratings,HttpStatus.OK);
    }
}
