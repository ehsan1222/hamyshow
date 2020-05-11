package ir.hamyiar.security.controller.movie;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.UUID;

@Controller
@RequestMapping("movies")
public class MoviesController {
    // TODO: I should add


    @GetMapping
    public String showMovies() {
        return "movies";
    }

    @GetMapping("{movieId}")
    public String showMovieDetail(@PathVariable("movieId") UUID movieId) {
        return "movie";
    }

    @GetMapping("suggestmovie")
    public String suggestMoviePage() {
        return "suggest-movie";
    }

    @PostMapping("suggestmovie")
    public String suggestMovie(@RequestParam("movieName") String movieName,
                               @RequestParam("imdbUrl") String imdbUrl) {

        return "redirect: /movies";
    }

    @GetMapping("{movieId}/{movieFileId}")
    @ResponseBody
    public ResponseEntity<Resource> downloadMovie(@PathVariable("movieId") UUID movieId,
                                                  @PathVariable("moviePathId") UUID moviePathId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("{movieId}/upload")
    public String uploadMovieForm(@PathVariable("movieId") String movieId) {

        return "upload-movie";
    }


    @PostMapping("{movieId}/upload")
    public String handleUploadMovieFile(@PathVariable("movieId") String movieId,
                                        @RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes) {


        redirectAttributes.addAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/movies/{movieId}/upload";
    }




}
