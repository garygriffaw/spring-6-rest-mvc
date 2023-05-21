package com.garygriffaw.spring6restmvc.controllers;

import com.garygriffaw.spring6restmvc.model.BeerDTO;
import com.garygriffaw.spring6restmvc.model.BeerStyle;
import com.garygriffaw.spring6restmvc.services.BeerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@Slf4j
@RequiredArgsConstructor
@RestController
public class BeerController {

    public static final String BEER_PATH = "/api/v1/beer";
    public static final String BEER_PATH_ID = BEER_PATH + "/{beerId}";
    private final BeerService beerService;

    @PatchMapping(BEER_PATH_ID)
    public ResponseEntity updateBeerPatchById(@PathVariable("beerId") UUID id, @RequestBody BeerDTO beer) {

        beerService.patchById(id, beer);

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping(BEER_PATH_ID)
//    @RequestMapping(value = "{beerId}", method = RequestMethod.DELETE)
    public ResponseEntity deleteById(@PathVariable("beerId") UUID id) {

        if (!beerService.deleteById(id)) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }
    @PutMapping(BEER_PATH_ID)
    public ResponseEntity updateById(@PathVariable("beerId") UUID id, @Validated @RequestBody BeerDTO beer) {

        if (beerService.updateBeerById(id, beer).isEmpty()) {
            throw new NotFoundException();
        }

        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

//    @RequestMapping(method = RequestMethod.POST)
    @PostMapping(BEER_PATH)
    public ResponseEntity handlePost(@Validated @RequestBody BeerDTO beer) {
        BeerDTO savedBeer = beerService.saveNewBeer(beer);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Location", "/api/v1/beer/" + savedBeer.getId().toString());

        return new ResponseEntity(headers, HttpStatus.CREATED);
    }

    @GetMapping(BEER_PATH)
    public List<BeerDTO> listBeers(@RequestParam(required = false) String beerName,
                                   @RequestParam(required = false) BeerStyle beerStyle,
                                   @RequestParam(required = false) Boolean showInventory) {

        return beerService.listBeers(beerName, beerStyle, showInventory);
    }

    @GetMapping(BEER_PATH_ID)
    public BeerDTO getBeerById(@PathVariable("beerId") UUID beerId) {
//        log.debug("Get beer by ID - in controller");
        return beerService.getBeerById(beerId).orElseThrow(NotFoundException::new);
    }
}
