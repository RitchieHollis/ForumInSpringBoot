import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { Category, Channel } from 'src/app/models/channel';

const BASE_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class ChannelService {

  constructor(private http: HttpClient) { }

  getChannelsByCategory(category : Category) : Observable<Channel[]> {
    return this.http.get<Channel[]>(BASE_URL + "channels", {
      params: {
        cat: category
      }
    })
  }


}
