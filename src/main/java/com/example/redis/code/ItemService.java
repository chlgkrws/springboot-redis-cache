package com.example.redis.code;

import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.cache.annotation.Caching;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ItemService {

    private final ItemJpaRepository itemJpaRepository;

    /**
     * 아이템 리스트 조회 (items 캐시)
     */
    @Cacheable(value = "itemsCache", key = "'items'")
    public List<Item> findAll() {
        return itemJpaRepository.findAll();
    }

    /**
     * 아이템 조회 (item id로 캐시)
     */
    @Cacheable(value = "itemsCache", key = "#id")
    public Item findItemById(Long id) {
        return itemJpaRepository.findById(id).orElse(null);
    }

    /**
     * 아이템 저장 (item id로 캐시, items 캐시 삭제)
     */
    @CachePut(value = "itemsCache", key = "#result.id")
    @CacheEvict(value = "itemsCache", key = "'items'")
    public Item save(ItemDto item) {
        var entity = new Item();
        entity.setName(item.getName());
        entity.setPrice(item.getPrice());

        return itemJpaRepository.save(entity);
    }

    /**
     * 아이템 삭제 (item id 삭제, items 캐시 삭제)
     */
    @Caching(evict = {
            @CacheEvict(value = "itemsCache", key = "#id"),
            @CacheEvict(value = "itemsCache", key = "'items'")
    })
    public void deleteItem(Long id) {
        itemJpaRepository.deleteById(id);
    }

}
