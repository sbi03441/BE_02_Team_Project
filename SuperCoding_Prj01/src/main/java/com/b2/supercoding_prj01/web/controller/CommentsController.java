package com.b2.supercoding_prj01.web.controller;

import com.b2.supercoding_prj01.dto.UserRequestDto;
import com.b2.supercoding_prj01.entity.CommentsEntity;
import com.b2.supercoding_prj01.entity.HeartEntity;
import com.b2.supercoding_prj01.jwt.JwtTokenProvider;
import com.b2.supercoding_prj01.repository.CommentsRepository;
import com.b2.supercoding_prj01.repository.HeartRepository;
import com.b2.supercoding_prj01.repository.UserRepository;
import com.b2.supercoding_prj01.service.HeartService;
import com.b2.supercoding_prj01.web.dto.UserDto;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/comments")
public class CommentsController {
    private final CommentsRepository commentsRepository;
    private final HeartRepository heartRepository;
    private final HeartService heartService;
    private final JwtTokenProvider jwtTokenProvider;



    // 해당 댓글에 좋아요 추가
    @Transactional
    @PostMapping("/heart")
    public ResponseEntity<String> addHeart(@RequestHeader("TOKEN") String token,
                                        @RequestParam Long postId
                                           ){
        if(token.isEmpty())
            return ResponseEntity.status(HttpStatus.FORBIDDEN).body("token이 없습니다.");
        else {
            String email = jwtTokenProvider.findEmailBytoken(token);
            return email.isBlank()? ResponseEntity.status(HttpStatus.FORBIDDEN).body("token이 없습니다."): heartService.clickHeart(postId, email);
        }
    }

    // 해당 댓글에 좋아요 정보
    @GetMapping("/{postId}/heart")
    public List<HeartEntity> findHeartToPostId(@PathVariable Long postId,
                                               @RequestBody UserDto dto){
        Optional<CommentsEntity> comments = commentsRepository.findByPostId(postId);

        return comments.map(heartRepository::findByComments).orElse(null);
//      = return comments.map(commentsEntity -> heartRepository.findByComments(commentsEntity)).orElse(null);
//      = if(comments.isPresent())
//            return heartRepository.findByComments(comments.get());
//        else return null;
    }
}
