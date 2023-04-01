
export class Goods{
    goodsId: any
    goodsName: any
    goodsImage: any
    goodsPrice: any
    goodsDiscount: any
    goodsType: any 
    count: any
    total: any
}

export class shoppingCart{
    shoppingCartId: any | null | undefined
    shoppingCartDataJson: string  | null | undefined
    orderPrice: number | null | undefined
    userId: number | null | undefined
}