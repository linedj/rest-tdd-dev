package com.example.rest_tdd.domain.post.post.service;

import com.example.rest_tdd.domain.member.member.entity.Member;
import com.example.rest_tdd.domain.post.post.entity.Post;
import com.example.rest_tdd.domain.post.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class PostService {

    private final PostRepository postRepository;

    public Post write(Member author, String title, String content, boolean published, boolean listed) {

        return postRepository.save(
                Post
                        .builder()
                        .author(author)
                        .title(title)
                        .content(content)
                        .published(published)
                        .listed(listed)
                        .build()
        );
    }

    public List<Post> getItems() {
        return postRepository.findAll();
    }

    public Optional<Post> getItem(long id) {
        return postRepository.findById(id);
    }

    public long count() {
        return postRepository.count();
    }

    public void delete(Post post) {
        postRepository.delete(post);
    }

    @Transactional
    public void modify(Post post, String title, String content) {
        post.setTitle(title);
        post.setContent(content);
    }

    public void flush() {
        postRepository.flush();
    }

    public Optional<Post> getLatestItem() {
        return postRepository.findTopByOrderByIdDesc();
    }
}