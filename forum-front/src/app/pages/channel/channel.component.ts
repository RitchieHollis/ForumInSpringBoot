import { Component, OnInit } from '@angular/core';
import { Category, Channel } from 'src/app/models/channel';
import { ChannelService } from './service/channel.service';
import { ActivatedRoute } from '@angular/router';


@Component({
  selector: 'app-channel',
  templateUrl: './channel.component.html',
  styleUrls: ['./channel.component.scss']
})
export class ChannelComponent implements OnInit {
  channels: Channel[] = []
  title!: string

  constructor(
    private channelService : ChannelService,
    private route : ActivatedRoute) {
    
  }
  ngOnInit(): void {
    this.route.params.subscribe(
      (params) => {
        const categoryString = params["category"] as string
        const categoryEnum = Category[categoryString as keyof typeof Category]
        if (categoryEnum) {
          this.channelService.getChannelsByCategory(categoryEnum)
            .subscribe((data : Channel[]) => {
              this.channels = data
              this.title = categoryString
            })
        }
      }
    )
  }
}
