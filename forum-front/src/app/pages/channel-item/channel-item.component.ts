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
  idChannel! : number
  id! : number

  constructor(
    private ChannelItemService : ChannelItemService,
    private route : ActivatedRoute) {
    
  }

  ngOnInit(): void {

    this.idChannel = this.route.snapshot.params["id"];
    
    // this.route.params.subscribe(
    //   (params) => {
    //     const idChannel = params["id"] as number
        if (this.idChannel) {
          this.ChannelItemService.getPostsByChannelId(this.idChannel)
            .subscribe((data : ListedPost[]) => {
              this.posts = data
              this.ChannelItemService.getNameOfChannelById(this.idChannel).subscribe(data => this.channelName = data)
              this.id = this.idChannel
            })
         }
      // }
    //)
  }

}
