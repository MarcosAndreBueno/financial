export interface Occurrence {
    id: string,
    amount: string,
    date: string,
    account: {
        id: string,
        name: string,
        status_active: boolean
    },
    type: {
        id: string,
        name: string,
        status_active: boolean
    },
    category: {
        id: string,
        name: string
        status_active: boolean
    },
    description: string,
}