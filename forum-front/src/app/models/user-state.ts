import { Status } from "./user-profile";

export interface UserState{

    id : number,
    login : string,
    status : Status,
    img : number[],
    role : Role
}

export enum Role{
    ADMIN = "ROLE_ADMIN", MODERATOR = "ROLE_MODERATOR", USER = "ROLE_USER"
}