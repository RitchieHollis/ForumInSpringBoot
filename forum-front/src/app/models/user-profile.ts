import { ListedMessage } from "./listed-message";

export interface UserProfile{

    id : number,
    login : string,
    img : number[],
    status : Status,
    bio : string,
    age : Date,
    badges : Badge[],
    numberMessages : number,
    //role : Role, //role to check
    messages : ListedMessage[]
}

export enum Status{
    ACTIVE, OFFLINE, DONTINTERRUPT, BRB
}
export enum Badge{
    BEGINNER, ADVANCED, CREATOR, VIP, COMMUNITY, VISUAL
}
