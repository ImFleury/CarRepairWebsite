package com.techelevator.controller;

import com.techelevator.dao.UserDao;
import com.techelevator.dao.VehicleDao;
import com.techelevator.model.User;
import com.techelevator.model.Vehicle;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.Principal;
import java.util.List;

@RestController
@CrossOrigin
@PreAuthorize("isAuthenticated()")
@RequestMapping("vehicle")
public class VehicleController {

    private VehicleDao vehicleDao;

    private UserDao userDao;

    public VehicleController(VehicleDao vehicleDao, UserDao userDao) {
        this.vehicleDao = vehicleDao;
        this.userDao = userDao;
    }

    /**
     * Returns the current logged-in user.
     * @param username The principal username,.
     * @return The current logged-in user.
     */
    public User getLoggedInUser(String username)
    {
        return this.userDao.findByUsername(username);
    }

    @RequestMapping(path = "/all", method = RequestMethod.GET)
    public List<Vehicle> listAll() {
        return vehicleDao.getAllVehicles();
    }

    @RequestMapping(method = RequestMethod.GET)
    public List<Vehicle> list(Principal principal) {

        User loggedInUser = getLoggedInUser(principal.getName());

        return vehicleDao.getVehicleByCustomerId(loggedInUser.getId());}

    @RequestMapping(method = RequestMethod.POST)
    public Vehicle create(@Valid @RequestBody Vehicle vehicle, Principal principal) {
        User loggedInUser = getLoggedInUser(principal.getName());

        int id = vehicleDao.addVehicle(loggedInUser.getId(), vehicle);
        vehicle.setVehicleId(id);
        return vehicle;
    }


    @RequestMapping(path = "/years", method = RequestMethod.GET)
    public String getVehicleYears() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-data.p.rapidapi.com/cars/years"))
                .header("X-RapidAPI-Key", "3ce156a0cbmsh3cdebac2f07584ap1f99dejsn462087aef6d1")
                .header("X-RapidAPI-Host", "car-data.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @RequestMapping(path = "/makes", method = RequestMethod.GET)
    public String getVehicleMakes() throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(URI.create("https://car-data.p.rapidapi.com/cars/makes"))
                .header("X-RapidAPI-Key", "3ce156a0cbmsh3cdebac2f07584ap1f99dejsn462087aef6d1")
                .header("X-RapidAPI-Host", "car-data.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

    @RequestMapping(path = "/models/{year}/{make}", method = RequestMethod.GET)
    public String getVehicleModels(@PathVariable String year, @PathVariable String make) throws IOException, InterruptedException {
        HttpRequest request = HttpRequest.newBuilder()
                .uri(UriComponentsBuilder.fromUriString("https://car-data.p.rapidapi.com/cars").queryParam("year", year).queryParam("make", make).build().toUri())
                .header("X-RapidAPI-Key", "3ce156a0cbmsh3cdebac2f07584ap1f99dejsn462087aef6d1")
                .header("X-RapidAPI-Host", "car-data.p.rapidapi.com")
                .method("GET", HttpRequest.BodyPublishers.noBody())
                .build();
        HttpResponse<String> response = HttpClient.newHttpClient().send(request, HttpResponse.BodyHandlers.ofString());
        return response.body();
    }

}
