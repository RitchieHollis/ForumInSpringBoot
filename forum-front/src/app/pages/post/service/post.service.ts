import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { Observable } from 'rxjs';
import { ShowMessage } from 'src/app/models/show-message';

const BASE_URL = "http://localhost:8080/"

@Injectable({
  providedIn: 'root'
})
export class PostService {

  constructor(private http: HttpClient) { }

  getMessagesByPostId(id : number): Observable<ShowMessage[]>{

    return this.http.get<ShowMessage[]>(BASE_URL + "/post", {
      params: {
        channel : id
      }
    })
  }
}
