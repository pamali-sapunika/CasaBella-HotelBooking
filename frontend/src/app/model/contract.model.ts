export interface Contract {
    contractId: number, 
    hotelId: number,
    startDate: string; // ISO format date string
    endDate: string; // ISO format date string
    cancellationDeadlineDays: number;
    cancellationDescription: string; 
    cancellationFeePercentage: number; 
    prepaymentFeePercenatage: number; 
    balancedPaymentDays: number; 
    balancedPaymentPercentage: number; 
  }
  