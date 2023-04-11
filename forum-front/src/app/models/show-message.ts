import { UserState } from "./user-state"

export interface ShowMessage{

    id: number,
    userLogin: string,
    userId : number
    created_at: Date,
    modified_at: Date,
    content: string,
    userState: UserState
}