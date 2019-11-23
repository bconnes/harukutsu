package com.brandon.harukutsu.api;

import com.brandon.harukutsu.model.WaniKaniUser;
import com.brandon.harukutsu.service.WaniKaniUserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

/**
 * @author bconnes
 * @since 11/21/2019
 */
@RequestMapping("api/v1/wk_user")
@RestController
public class WaniKaniUserController
{
    private final WaniKaniUserService waniKaniUserService;

    @Autowired
    public WaniKaniUserController(WaniKaniUserService waniKaniUserService)
    {
        this.waniKaniUserService = waniKaniUserService;
    }

    @PostMapping
    public int addWaniKaniUser(@RequestBody WaniKaniUser waniKaniUser)
    {
        return waniKaniUserService.addWaniKaniUser(waniKaniUser);
    }

    @GetMapping(path = "{id}")
    public WaniKaniUser getWaniKaniUser(@PathVariable("id") UUID id)
    {
        return waniKaniUserService.getWaniKaniUserById(id)
            .orElse(null);
    }

    @GetMapping
    public List<WaniKaniUser> getAllWaniKaniUsers()
    {
        return waniKaniUserService.getWaniKaniUsers();
    }
}
