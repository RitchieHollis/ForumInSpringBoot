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
  postName! : string
  idPost! : number

  constructor(
    private PostService : PostService,
    private route : ActivatedRoute) {
    
  }

  ngOnInit(): void {
    
    this.route.params.subscribe(
      (params) => {
        this.idPost = params["id"] as number
        if (this.idPost) {
          this.PostService.getMessagesByPostId(this.idPost)
            .subscribe((data : ShowMessage[]) => {
              this.messages = data
              this.PostService.getNameById(this.idPost)
              .subscribe(({name}) => this.postName = name)
            })
        }
      }
    )
  }
}
