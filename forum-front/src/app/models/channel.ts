import { LatestPost } from "./latest-post";

export interface Channel {
    id : number,
    title : string,
    numberPosts : number,
    latestPost : LatestPost,
    category : string
}

export enum Category {
    GENERAL = "GENERAL", CINEMA = "CINEMA", MUSIC = "MUSIC", ART = "ART", IT = "IT", LIFESTYLE = "LIFESTYLE"
}