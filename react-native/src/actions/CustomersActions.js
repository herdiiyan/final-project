export const FIND_CUSTOMER = "FIND_CUSTOMER";
export const FIND_CUSTOMER_SUCCES = "FIND_CUSTOMER_SUCCES";
export const FIND_CUSTOMER_ERROR = "FIND_CUSTOMER_ERROR";

export function findOneCustomers(cif) {
  return {
    type: FIND_CUSTOMER,
    cif: cif
  };
}
