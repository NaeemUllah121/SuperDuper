package com.zasa.superduper.Retrofit;

import com.zasa.superduper.CategoryListCompanyWise.CategoryItemCombineApi;
import com.zasa.superduper.CategoryListCompanyWise.ChildItemApi;
import com.zasa.superduper.CategoryListCompanyWise.ItemListCompanyWiseApi;
import com.zasa.superduper.CategoryListCompanyWise.ParentItemApi;
import com.zasa.superduper.CompanyCategoryListTypeWiseS.CompanyCategoryListApi;
import com.zasa.superduper.CompanyListCategoryWiseS.BranchListCategoryWiseApiResponse;
import com.zasa.superduper.CourierService.Models.CourierListApi;
import com.zasa.superduper.CourierService.Models.DeliveryRequestHistoryApi;
import com.zasa.superduper.CourierService.Models.DeliveryTypeListApi;
import com.zasa.superduper.CourierService.Models.ParcelTypeListApi;
import com.zasa.superduper.CourierService.Models.SubmitDeliveryRequestApi;
import com.zasa.superduper.CourierService.Models.SubmitDeliveryRequestList;
import com.zasa.superduper.DonationVerification.UnVerifyFamilyOrderApi;
import com.zasa.superduper.DonationVerification.VerifyFOrderApi;
import com.zasa.superduper.FAQs.FaqListApi;
import com.zasa.superduper.Fragments.DesignationListApi;
import com.zasa.superduper.Fragments.ProfessionListApi;
import com.zasa.superduper.Home.TopBrandListApi;
import com.zasa.superduper.Profile.UpdateMemberApi;
import com.zasa.superduper.Redeem.CouponListApi;
import com.zasa.superduper.Splash.CompanyDetailsApi;
import com.zasa.superduper.TechClub.Model.TechMemTypeListApi;
import com.zasa.superduper.TechClub.Model.TechMembersListApi;
import com.zasa.superduper.TechClub.Model.TechTeamListApi;
import com.zasa.superduper.TechClub.Model.TechTopMembersListApi;
import com.zasa.superduper.WasteMaterialRequestSubmit.MOrderList;
import com.zasa.superduper.WasteMaterialRequestSubmit.MOrderListModelSubmitApi;
import com.zasa.superduper.WasteMaterialRequestHistory.MRHistoryApi;
import com.zasa.superduper.WasteMaterialScanner.CheckMaterialItemApi;
import com.zasa.superduper.SubmitGeneralOrder.OrderModel;
import com.zasa.superduper.Home.CompanyTypeListApi;
import com.zasa.superduper.Home.SliderApi;
import com.zasa.superduper.Profile.MemberDetailsApi;
import com.zasa.superduper.RegisterUser.AddMemberApi;
import com.zasa.superduper.Login.LoginApi;
import com.zasa.superduper.RegisterUser.AreaListApi;
import com.zasa.superduper.RegisterUser.CityListApi;
import com.zasa.superduper.RegisterUser.CountryListApi;
import com.zasa.superduper.Splash.AppVersionApi;
import com.zasa.superduper.CompanyListTypeWise.CompanyListTypeWiseApi;
import com.zasa.superduper.SubmitGeneralOrder.SubmitOrderApi;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

public interface APIInterface {


    //////////////////////Get APIs////////////////////////

    //////Delivery Type list api//////
    @GET("List/DeliveryTypelist")
    Call<DeliveryTypeListApi> mDeliveryTypeListApi();

    //////Parcel Type list api//////
    @GET("List/ParcelTypelist")
    Call<ParcelTypeListApi> mParcelTypeListApi();

    //////CompanyTypeList api//////
    @GET("List/CompanyTypelist")
    Call<CompanyTypeListApi> mCompanyTypeListApi();

    //////CountryList api//////
    @GET("List/CountryList")
    Call<CountryListApi> mCountryApi();


    //////CompanyList api//////
    @GET("List/Companylist")
    Call<CompanyListTypeWiseApi> mCompanyListApi();

    //////Courierlist api//////
    @GET("List/Courierlist")
    Call<CourierListApi> mCourierListApi();

    //////Faqlist api//////
    @GET("List/Faqlist")
    Call<FaqListApi> mFaqListApi();


    //////Top Brand list api//////
    @GET("List/TopBrandlist")
    Call<TopBrandListApi> mTopBrandListApi();

    /*//////Top CompanyCategory list api//////
    @GET("List/CompanyCategorylist")
    Call<CompanyCategoryListApi> mCompanyCategoryListApi();*/


    //////Designation list api//////
    @GET("List/Designationlist")
    Call<DesignationListApi> mDesignationListApi();

    //////Profession list api//////
    @GET("List/Professionlist")
    Call<ProfessionListApi> mProfessionListApi();



    //////Tech Member Type list api//////
    @GET("Tech/TechMTypelist")
    Call<TechMemTypeListApi> mTechMemTypeListApi();


    //////Tech Team list api//////
    @GET("Tech/TechTeam")
    Call<TechTeamListApi> mTechTeamListApi();

    //////Tech Top Members list api//////
    @GET("Tech/TechTop")
    Call<TechTopMembersListApi> mTechTopMembersListApi();

    //////Tech Member list api//////
    @GET("Tech/TechMembers")
    Call<TechMembersListApi> mTechMembersListApi();






    //////////////////////Post APIs////////////////////////

    //////Splash screen api//////
    @FormUrlEncoded
    @POST("Common/Appversion")
    Call<AppVersionApi> mAppVersionApi(
            @Field("App_ID") String appid
    );

    //////Add member Api//////
    @FormUrlEncoded
    @POST("Account/AddMem")
    Call<AddMemberApi> mAddMemberApi(
            @Field("Member_FName") String firstName,
            @Field("Member_LName") String lastName,
            @Field("Member_Mobile") String mobile,
            @Field("Member_Email") String Member_Email,
            @Field("Member_DOB") String Member_DOB,
            @Field("Country_Id") String Country_Id,
            @Field("City_Id") String City_Id,
            @Field("Area_Id") String Area_Id,
            @Field("Member_LoginPass") String Pass,
            @Field("Member_CNIC") String Member_CNIC,
            @Field("Member_Gender") String Member_Gender,
            @Field("Member_Address") String Member_Address
      /*      @Field("Member_LoginId") String LoginId,
            @Field("Member_LoginPass") String LoginPass*/
    );


    //////Update member Api//////
    @FormUrlEncoded
    @POST("Account/UpdateMem")
    Call<UpdateMemberApi> mUpdateMemberApi(
            @Field("Member_Unique") String Member_Unique,
            @Field("Member_FName") String firstName,
            @Field("Member_LName") String lastName,
            @Field("Member_Mobile") String mobile,
            @Field("Member_DOB") String Member_DOB,
            @Field("Member_Email") String Member_Email,
            @Field("Country_Id") String Country_Id,
            @Field("City_Id") String City_Id,
            @Field("Area_Id") String Area_Id,
            @Field("Member_CNIC") String Member_CNIC,
            @Field("Member_Address") String Member_Address,
            @Field("Member_Image_String") String Member_Image_String,
            @Field("Member_Profession") String Member_Profession,
            @Field("Member_Designation") String Member_Designation
//            @Field("Payment_Slip_Number") String Payment_Slip_Number,
//            @Field("Payment_Image_String") String Payment_Image_String,
//            @Field("Payment_Type") int Payment_Type

    );

    //////Update member Api//////used for email verification
    @FormUrlEncoded
    @POST("Account/UpdateMem")
    Call<UpdateMemberApi> mEmailVerification(
            @Field("Member_Unique") String Member_Unique,
            @Field("Member_Mobile") String mobile,
            @Field("Email_Verify") String Email_Verify

    );

    //////Update member Api//////used for update membership
    @FormUrlEncoded
    @POST("Account/UpdateMem")
    Call<UpdateMemberApi> mUpdateMembershipApi(
            @Field("Member_Unique") String Member_Unique,
            @Field("Member_FName") String firstName,
            @Field("Member_LName") String lastName,
            @Field("Member_Mobile") String mobile,
            @Field("Member_DOB") String Member_DOB,
            @Field("Member_Email") String Member_Email,
            @Field("Country_Id") String Country_Id,
            @Field("City_Id") String City_Id,
            @Field("Area_Id") String Area_Id,
            // @Field("Member_CNIC") String Member_CNIC,
            // @Field("Member_Image_String") String Member_Image_String,

            @Field("Member_Address") String Member_Address,
            @Field("Member_Profession") String Member_Profession,
            @Field("Member_Designation") String Member_Designation,
            @Field("Payment_Slip_Number") String Payment_Slip_Number,
            @Field("Payment_Image_String") String Payment_Image_String,
            @Field("Payment_Type") int Payment_Type

    );


    //////Login Api//////
    @FormUrlEncoded
    @POST("Account/Login")
    Call<LoginApi> mLoginApi(
            @Field("Member_LoginId") String LoginId,
            @Field("Member_LoginPass") String LoginPass

    );

    //////City List Api//////
    @FormUrlEncoded
    @POST("List/CityList")
    Call<CityListApi> mCityListApi(
            @Field("Country_Id") String Country_Id
    );

    //////Area List Api//////
    @FormUrlEncoded
    @POST("List/AreaList")
    Call<AreaListApi> mAreaListApi(
            @Field("City_Id") String City_Id

    );

    //////Sliders api//////
    @FormUrlEncoded
    @POST("Common/Sliders")
    Call<SliderApi> mSliderApi(
            @Field("APP_ID") String appid
    );

    //////CompanyListTypeWise Api//////
    @FormUrlEncoded
    @POST("List/CompanyListTypeWise")
    Call<CompanyListTypeWiseApi> mCompanyListTypeWiseApi(
            @Field("Company_Type_Code") String CompanyTypeCode
    );


    //////Member Details api//////
    @FormUrlEncoded
    @POST("Account/MemberDetails")
    Call<MemberDetailsApi> mMemberDetailsApi(
            @Field("Member_Unique") String Member_Unique
    );

    /////Item Cat Company Wise List///////
    @FormUrlEncoded
    @POST("List/ItemCatCompanyWiseList")
    Call<ParentItemApi> mParentItemApi(
            @Field("Company_Code") String Company_Code
    );

    /////Item Cat Wise List///////
    @FormUrlEncoded
    @POST("List/ItemCatWiseList")
    Call<ChildItemApi> mChildItemApi(
            @Field("Company_Code") String Company_Code,
            @Field("Item_Category_Code") String Item_Category_Code
    );

///////////////////////////////////////////////////////extra
    /////Item List (Company Wise)///////
    @FormUrlEncoded
    @POST("List/ItemCompanyWiseList")
    Call<ItemListCompanyWiseApi> mItemListCompanyWiseApi(
            @Field("Company_Code") String Company_Code);


    /////Category Item Model Combine ///////
    @FormUrlEncoded
    @POST("List/CatItemModel")
    Call<CategoryItemCombineApi> mCategoryItemCombineApi(
            @Field("Company_Code") String Company_Code);
//////////////////////////////////////////////extra

    /////submit Order api///////
    @POST("Order/SubmitOrder")
    Call<SubmitOrderApi> mSubmitOrder(
            @Body OrderModel orderModel
    );

    /////Check Material///////
    @FormUrlEncoded
    @POST("List/CheckMaterial")
    Call<CheckMaterialItemApi> mCheckMaterialApi(
            @Field("Material_Code") String Material_Code
    );


    /////submit Material Order api///////
    @POST("Order/SubmitMOrder")
    Call<MOrderListModelSubmitApi> mSubmitMOrderApi(
            @Body MOrderList mOrderList
    );


    /////Material Request History///////
    @FormUrlEncoded
    @POST("Order/MRHistory")
    Call<MRHistoryApi> mMRHistoryApi(
            @Field("Member_Unique") String Member_Unique
    );

    /////Vendor Delivery Request History///////
    @FormUrlEncoded
    @POST("Delivery/VDRHistory")
    Call<DeliveryRequestHistoryApi> mDeliveryRequestHistoryApi(
            @Field("Vendor_Unique") String Vendor_Unique,
            @Field("Member_Unique") String Member_Unique
    );

    /////Submit Delivery Request api///////
    @POST("Delivery/SubmitDeliveryR")
    Call<SubmitDeliveryRequestApi> mSubmitDeliveryRequestApi(
            @Body SubmitDeliveryRequestList submitDeliveryRequestList
    );

    //////Category list TypeWise api//////
    @FormUrlEncoded
    @POST("List/CategorylistTypeWise")
    Call<CompanyCategoryListApi> mCompanyCategoryListApi(
            @Field("Company_Type_Code") String Company_Type_Code
    );

    //////Branch List Category Wise api//////
    @FormUrlEncoded
    @POST("List/BranchListCategoryWise")
    Call<BranchListCategoryWiseApiResponse> mBranchListCategoryWiseApiResponse(
            @Field("Company_Category_Code") String Company_Category_Code
    );

    //////UnVerify Family Order api//////
    @FormUrlEncoded
    @POST("List/UverifyFOByMember")
    Call<UnVerifyFamilyOrderApi> mUnVerifyFamilyOrderApi(
            @Field("Member_Unique") String Member_Unique
    );

    //////Verify F Order api//////
    @FormUrlEncoded
    @POST("Account/VerifyFOrder")
    Call<VerifyFOrderApi> mVerifyFOrderApi(
            @Field("Member_Unique") String Member_Unique,
            @Field("Order_Id") String Order_Id
    );

    //////Coupon List api//////
    @FormUrlEncoded
    @POST("Common/CouponList")
    Call<CouponListApi> mCouponListApi(
            @Field("Member_Unique") String Member_Unique
    );


    @FormUrlEncoded
    @POST("List/CompanyDetails")
    Call<CompanyDetailsApi> mCompanyDetail(
            @Field("Company_Code") String Company_Code
    );

}
