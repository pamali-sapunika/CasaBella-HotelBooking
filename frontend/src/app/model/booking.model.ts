export interface Booking{
    bookingId: number;
    contractId: number;
    discountId: number;
    userId: number;
    passengerId: number;
    checkinDate: string;
    checkoutDate: string;
    bookingDate: string;
    adultCount: number;
    childCount: number;
    totalAmount: number;
    discountAmount: number;
    netAmount: number;
    prepaymentAmount: number;
    balancedAmount: number;
    status: string;
}