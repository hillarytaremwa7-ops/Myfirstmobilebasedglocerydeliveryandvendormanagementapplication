package com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.local

import androidx.room.TypeConverter
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AccountStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.AvailabilityStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DeliveryStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.DisputeStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.OrderStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PaymentMethod
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.PaymentStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.RequestStatus
import com.example.myfirstmobilebasedglocerydeliveryandvendormanagementapplication.data.model.UserRole

class Converters {
    @TypeConverter
    fun fromPaymentMethod(value: PaymentMethod): String = value.name

    @TypeConverter
    fun toPaymentMethod(value: String): PaymentMethod = try { PaymentMethod.valueOf(value) } catch (e: Exception) { PaymentMethod.MOBILE_MONEY }

    @TypeConverter
    fun fromPaymentStatus(value: PaymentStatus): String = value.name

    @TypeConverter
    fun toPaymentStatus(value: String): PaymentStatus = try { PaymentStatus.valueOf(value) } catch (e: Exception) { PaymentStatus.PENDING }

    @TypeConverter
    fun fromOrderStatus(value: OrderStatus): String = value.name

    @TypeConverter
    fun toOrderStatus(value: String): OrderStatus = try { OrderStatus.valueOf(value) } catch (e: Exception) { OrderStatus.PENDING }

    @TypeConverter
    fun fromUserRole(value: UserRole): String = value.name

    @TypeConverter
    fun toUserRole(value: String): UserRole = try { UserRole.valueOf(value) } catch (e: Exception) { UserRole.CUSTOMER }

    @TypeConverter
    fun fromAccountStatus(value: AccountStatus): String = value.name

    @TypeConverter
    fun toAccountStatus(value: String): AccountStatus = try { AccountStatus.valueOf(value) } catch (e: Exception) { AccountStatus.APPROVED }

    @TypeConverter
    fun fromAvailabilityStatus(value: AvailabilityStatus): String = value.name

    @TypeConverter
    fun toAvailabilityStatus(value: String): AvailabilityStatus = try { AvailabilityStatus.valueOf(value) } catch (e: Exception) { AvailabilityStatus.ONLINE }

    @TypeConverter
    fun fromDeliveryStatus(value: DeliveryStatus): String = value.name

    @TypeConverter
    fun toDeliveryStatus(value: String): DeliveryStatus = try { DeliveryStatus.valueOf(value) } catch (e: Exception) { DeliveryStatus.UNASSIGNED }

    @TypeConverter
    fun fromRequestStatus(value: RequestStatus): String = value.name

    @TypeConverter
    fun toRequestStatus(value: String): RequestStatus = try { RequestStatus.valueOf(value) } catch (e: Exception) { RequestStatus.PENDING }

    @TypeConverter
    fun fromDisputeStatus(value: DisputeStatus): String = value.name

    @TypeConverter
    fun toDisputeStatus(value: String): DisputeStatus = try { DisputeStatus.valueOf(value) } catch (e: Exception) { DisputeStatus.OPEN }
}
