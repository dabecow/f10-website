package team.f10.service.impl;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import team.f10.exception.TagAlreadyExistsException;
import team.f10.model.Tag;
import team.f10.repository.TagRepository;
import team.f10.service.TagService;

import java.util.Set;

@Service
@AllArgsConstructor
public class TagServiceImpl implements TagService {

    private final TagRepository tagRepository;

    @Override
    public Set<Tag> getTags(Set<Long> ids) {
        return tagRepository.findByIdIn(ids);
    }

    @Override
    public void addTag(String tagName) {
        if (tagRepository.existsTagByName(tagName))
            throw new TagAlreadyExistsException(tagName);

        Tag tag = Tag.builder().name(tagName).build();
        tagRepository.save(tag);
    }

    @Override
    public void removeTag(Long id) {
        tagRepository.deleteById(id);
    }
}
