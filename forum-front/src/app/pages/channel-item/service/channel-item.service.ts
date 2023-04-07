import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ChannelName } from 'src/app/models/channel-name';
import { ListedPost } from 'src/app/models/listed-post';

const BASE_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class ChannelItemService {

  constructor(private http: HttpClient) { }

  getPostsByChannelId(id : number) : Observable<ListedPost[]>{
  
    return this.http.get<ListedPost[]>(BASE_URL + "channels/posts", {
      params: {
        channel : id
      }
    })
  }

  getNameOfChannelById(id : number) : Observable<ChannelName>{
    
    return this.http.get<ChannelName>(BASE_URL + "channels/name", {
      params:{
        id : id
      }
    })
  }
}
