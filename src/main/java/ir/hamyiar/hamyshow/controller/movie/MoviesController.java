package ir.hamyiar.hamyshow.controller.movie;

import org.springframework.core.io.Resource;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
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

    @GetMapping("suggestmovie")
    @PreAuthorize("hasAuthority('movie:suggest')")
    public String suggestMoviePage() {
        return "suggest-movie";
    }

    @PostMapping("suggestmovie")
    @PreAuthorize("hasAuthority('movie:suggest')")
    public String suggestMovie(@RequestParam("movieName") String movieName,
                               @RequestParam("imdbUrl") String imdbUrl) {

        return "redirect:/movies/suggestmovie";
    }

    @GetMapping("{movieId}")
    public String showMovieDetail(@PathVariable("movieId") UUID movieId) {
        return "movie-details";
    }

    @GetMapping("{movieId}/{moviePathId}")
    @ResponseBody
    @PreAuthorize("hasAuthority('movie:download')")
    public ResponseEntity<Resource> downloadMovie(@PathVariable("movieId") UUID movieId,
                                                  @PathVariable("moviePathId") UUID moviePathId) {

        return new ResponseEntity<>(HttpStatus.OK);
    }

    @GetMapping("upload")
    @PreAuthorize("hasAuthority('movie:upload')")
    public String uploadMovieForm() {

        return "upload-movie";
    }


    @PostMapping("upload")
    @PreAuthorize("hasAuthority('movie:upload')")
    public String handleUploadMovieFile(@RequestParam("file") MultipartFile file,
                                        RedirectAttributes redirectAttributes) {


        redirectAttributes.addAttribute("message",
                "You successfully uploaded " + file.getOriginalFilename() + "!");
        return "redirect:/movies/{movieId}/upload";
    }




}
