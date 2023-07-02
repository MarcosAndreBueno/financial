export interface Occurrence {
    _id: number,
    amount: string,
    date: string,
    type: {
        _id: number,
        type: string
    },
    category: {
        _id: number,
        category: string
    },
    description: string,
}