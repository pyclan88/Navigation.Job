package ru.practicum.android.diploma.data.dto.vacancy

import com.google.gson.annotations.SerializedName

data class ItemDto(
    @SerializedName("accept_incomplete_resumes")
    val acceptIncompleteResumes: Boolean,
    @SerializedName("accept_temporary")
    val acceptTemporary: Boolean,
    @SerializedName("address")
    val address: AddressDto,
    @SerializedName("adv_context")
    val advContext: Any,
    @SerializedName("adv_response_url")
    val advResponseUrl: Any,
    @SerializedName("alternate_url")
    val alternateUrl: String,
    @SerializedName("apply_alternate_url")
    val applyAlternateUrl: String,
    @SerializedName("archived")
    val archived: Boolean,
    @SerializedName("area")
    val area: AreaDto?,
    @SerializedName("branding")
    val branding: BrandingDto,
    @SerializedName("contacts")
    val contacts: Any,
    @SerializedName("created_at")
    val createdAt: String,
    @SerializedName("department")
    val department: DepartmentDto,
    @SerializedName("employer")
    val employer: EmployerDto,
    @SerializedName("employment")
    val employment: EmploymentDto?,
    @SerializedName("experience")
    val experience: ExperienceDto?,
    @SerializedName("has_test")
    val hasTest: Boolean,
    @SerializedName("id")
    val id: String,
    @SerializedName("insider_interview")
    val insiderInterview: Any,
    @SerializedName("is_adv_vacancy")
    val isAdvVacancy: Boolean,
    @SerializedName("name")
    val name: String,
    @SerializedName("premium")
    val premium: Boolean,
    @SerializedName("professional_roles")
    val professionalRoles: List<ProfessionalRoleDto>,
    @SerializedName("published_at")
    val publishedAt: String,
    @SerializedName("relations")
    val relations: List<Any>,
    @SerializedName("response_letter_required")
    val responseLetterRequired: Boolean,
    @SerializedName("response_url")
    val responseUrl: Any,
    @SerializedName("salary")
    val salary: SalaryDto?,
    @SerializedName("schedule")
    val schedule: ScheduleDto?,
    @SerializedName("show_logo_in_search")
    val showLogoInSearch: Boolean,
    @SerializedName("snippet")
    val snippet: SnippetDto,
    @SerializedName("sort_point_distance")
    val sortPointDistance: Any,
    @SerializedName("type")
    val type: TypeDto,
    @SerializedName("url")
    val url: String,
    @SerializedName("working_days")
    val workingDays: List<Any>,
    @SerializedName("working_time_intervals")
    val workingTimeIntervals: List<Any>,
    @SerializedName("working_time_modes")
    val workingTimeModes: List<Any>
)
