import com.google.gson.annotations.SerializedName


data class Transaction (
	@SerializedName("id") val id : Int?,
	@SerializedName("date") val date : String?,
	@SerializedName("amount") val amount : Double?,
	@SerializedName("fee") val fee : Double?,
	@SerializedName("description") val description : String?
)