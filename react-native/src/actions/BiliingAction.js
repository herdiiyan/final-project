export const FIND_BILLING = "FIND_BILLING";
export const FIND_BILLING_SUCCES = "FIND_BILLING_SUCCES";
export const FIND_BILLING_ERROR = "FIND_BILLING_ERROR";

export function findBilling(loanId) {
  return {
    type: FIND_BILLING,
    loanId: loanId
  };
}