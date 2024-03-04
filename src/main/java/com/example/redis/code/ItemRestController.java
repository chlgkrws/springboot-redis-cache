package com.example.redis.code;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/items")
@RequiredArgsConstructor
public class ItemRestController {

    private final ItemService itemService;

    /**
     * 전체 아이템 조회
     */
    @GetMapping
    public ResponseEntity findAll() {
        var items = itemService.findAll();

        return ResponseEntity.ok(items);
    }

    /**
     * 단일 아이템 조회
     */
    @GetMapping("/{id}")
    public ResponseEntity find(@PathVariable Long id) {
        var item = itemService.findItemById(id);

        return ResponseEntity.ok(item);
    }


    /**
     * 아이템 저장
     */
    @PostMapping
    public ResponseEntity save(@RequestBody ItemDto item) {
        itemService.save(item);

        return ResponseEntity.ok().build();
    }

    /**
     * 아이템 삭제
     */
    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        itemService.deleteItem(id);

        return ResponseEntity.ok().build();
    }
}
