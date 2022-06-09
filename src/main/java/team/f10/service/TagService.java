package team.f10.service;

import team.f10.model.Tag;

import java.util.Set;

public interface TagService {

    Set<Tag> getTags(Set<Long> ids);
    void addTag(String tagName);
    void removeTag(Long id);
}
