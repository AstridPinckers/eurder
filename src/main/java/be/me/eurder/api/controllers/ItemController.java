package be.me.eurder.api.controllers;

import be.me.eurder.service.ItemService;
import be.me.eurder.service.dtos.CreateItemDto;
import be.me.eurder.service.dtos.ReceiveItemDto;
import be.me.eurder.service.SecurityService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/items")
public class ItemController {

    Logger logger = LoggerFactory.getLogger(ItemController.class);

    private final ItemService itemService;
    private final SecurityService securityService;

    @Autowired
    public ItemController(ItemService itemService, SecurityService securityService) {
        this.itemService = itemService;
        this.securityService = securityService;
    }

    @GetMapping(produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public List<ReceiveItemDto> getAllItems(){
        logger.info("Item list requested");
        return itemService.getAllItems();
    }

    @PostMapping(consumes = "application/json", produces = "application/json")
    @ResponseStatus(HttpStatus.CREATED)
    public ReceiveItemDto createItem(@RequestHeader("email") Optional<String> email,
                                     @RequestHeader("password") Optional<String> rawPassword,
                                     @RequestBody CreateItemDto createItemDto) {
        logger.info("Attempt to create new item");
        securityService.assertValidCredentialsForAdmin(email, rawPassword);
        return itemService.createItem(createItemDto);
    }
}
