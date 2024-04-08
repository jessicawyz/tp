package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Address;
import seedu.address.model.person.Email;
import seedu.address.model.person.Exam;
import seedu.address.model.person.Id;
import seedu.address.model.person.Log;
import seedu.address.model.person.LogList;
import seedu.address.model.person.Name;
import seedu.address.model.person.Payment;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.Subject;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 * Note: This class does not handle the {@link Payment} field of {@link Person},
 * as payment modifications are intended to be managed exclusively through
 * dedicated payment commands to ensure controlled updates.
 */
class JsonAdaptedPerson {
    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String address;
    private final String subject;
    private final String uniqueId;
    private final String payment;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();
    private final List<JsonAdaptedExam> exams = new ArrayList<>();
    private final List<JsonAdaptedLog> logs = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("address") String address,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags, @JsonProperty("subject") String subject,
                             @JsonProperty("uniqueId") String uniqueId,
                             @JsonProperty("exams") List<JsonAdaptedExam> exams,
                             @JsonProperty("payment") String payment,
                             @JsonProperty("logs") List<JsonAdaptedLog> logs) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.address = address;
        this.subject = subject;
        this.uniqueId = uniqueId;
        this.payment = payment;
        if (tags != null) {
            this.tags.addAll(tags);
        }

        if (exams != null) {
            this.exams.addAll(exams);
        }

        if (logs != null) {
            this.logs.addAll(logs);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        address = source.getAddress().value;
        tags.addAll(source.getTags().stream()
                .map(JsonAdaptedTag::new)
                .collect(Collectors.toList()));
        subject = source.getSubject().value;
        uniqueId = source.getUniqueId().id;
        exams.addAll(source.getExams().stream()
                .map(JsonAdaptedExam::new)
                .collect(Collectors.toList()));
        payment = source.getPayment().value;
        logs.addAll(source.getLogs().getList().stream()
                .map(JsonAdaptedLog::new)
                .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }


        final List<Exam> personExams = new ArrayList<>();
        for (JsonAdaptedExam exam : exams) {
            if (!exam.isExamOverdue(exam)) {
                personExams.add(exam.toModelType());
            } 
        }

        final List<Log> personLogs = new ArrayList<>();
        for (JsonAdaptedLog log : logs) {
            personLogs.add(log.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (address == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Address.class.getSimpleName()));
        }
        if (!Address.isValidAddress(address)) {
            throw new IllegalValueException(Address.MESSAGE_CONSTRAINTS);
        }
        if (!Subject.isValidSubject(subject)) {
            throw new IllegalValueException(Subject.MESSAGE_CONSTRAINTS);
        }
        if (uniqueId == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Id.class.getSimpleName()));
        }
        if (!Payment.isValidPayment(payment)) {
            throw new IllegalValueException(Payment.MESSAGE_CONSTRAINTS);
        }
        if (payment == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Payment.class.getSimpleName()));
        }
        final Id modelId = new Id(uniqueId);
        final Subject modelSubject = new Subject(subject);
        final Address modelAddress = new Address(address);
        final Set<Tag> modelTags = new HashSet<>(personTags);
        final Set<Exam> modelExams = new HashSet<>(personExams);
        final Payment modelPayment = new Payment(payment);
        final LogList modelLogs = new LogList(personLogs);

        return new Person(modelName, modelPhone, modelEmail, modelAddress,
                modelTags, modelSubject, modelId, modelExams, modelPayment, modelLogs);
    }
}
