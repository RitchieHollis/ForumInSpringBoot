import { Component, OnInit } from '@angular/core';
import { ListedPost } from 'src/app/models/listed-post'
import { ChannelItemService } from './service/channel-item.service';
import { ActivatedRoute } from '@angular/router';
import { ChannelName } from 'src/app/models/channel-name';

@Component({
  selector: 'app-channel-item',
  templateUrl: './channel-item.component.html',
  styleUrls: ['./channel-item.component.scss']
})
export class ChannelItemComponent implements OnInit{

  posts : ListedPost[] = []
  channelName! : ChannelName

  constructor(
    private ChannelItemService : ChannelItemService,
    private route : ActivatedRoute) {
    
  }

  ngOnInit(): void {
    
    this.route.params.subscribe(
      (params) => {
        const idChannel = params["id"] as number
        if (idChannel) {
          this.ChannelItemService.getPostsByChannelId(idChannel)
            .subscribe((data : ListedPost[]) => {
              this.posts = data
              this.channelName = this.ChannelItemService.getNameOfChannelById(idChannel).subscribe
            })
        }
      }
    )
  }

}
