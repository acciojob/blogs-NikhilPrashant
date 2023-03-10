package com.driver.services;

import com.driver.models.*;
import com.driver.models.Image;
import com.driver.repositories.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    @Autowired
    BlogRepository blogRepository2;
    @Autowired
    ImageRepository imageRepository2;

    public Image addImage(Integer blogId, String description, String dimensions){
        //add an image to the blog
        Blog blog = blogRepository2.findById(blogId).get();
        Image image = new Image(description, dimensions, blog);
        blog.getImageList().add(image);
        blogRepository2.save(blog);
        return image;
    }

    public void deleteImage(Integer id){
        imageRepository2.deleteById(id);
    }

    public int countImagesInScreen(Integer id, String screenDimensions) {
        //Find the number of images of given dimensions that can fit in a screen having `screenDimensions`
        Image image = imageRepository2.findById(id).get();
        String[] imageDmns = image.getDimensions().split("X");
        String[] screenDmns = screenDimensions.split("X");
        int noOfImagesPossible = (Integer.parseInt(screenDmns[0]) / Integer.parseInt(imageDmns[0])) * (Integer.parseInt(screenDmns[1]) / Integer.parseInt(imageDmns[1]));
        return noOfImagesPossible;
    }
}
