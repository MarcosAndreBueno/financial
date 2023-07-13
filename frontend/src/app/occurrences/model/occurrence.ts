export interface Occurrence {
    id: string,
    amount: string,
    date: string,
    account: string,
    type: {
        id: string,
        type: string
    },
    category: {
        id: string,
        category: string
    },
    description: string,
}