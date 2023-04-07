import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { ShowMessage } from 'src/app/models/show-message';
import { PostService } from './service/post.service';

@Component({
  selector: 'app-post',
  templateUrl: './post.component.html',
  styleUrls: ['./post.component.scss']
})
export class PostComponent implements OnInit{

  messages: ShowMessage[] = []

  constructor(
    private PostService : PostService,
    private route : ActivatedRoute) {
    
  }

  ngOnInit(): void {
    
    this.route.params.subscribe(
      (params) => {
        const idPost = params["id"] as number
        if (idPost) {
          this.PostService.getMessagesByPostId(idPost)
            .subscribe((data : ShowMessage[]) => {
              this.messages = data
            })
        }
      }
    )
  }
}
