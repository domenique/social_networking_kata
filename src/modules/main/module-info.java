module main {
    requires domain;
    requires application;
    requires presentation;
    uses io.tripled.social.client.domain.SocialNetworkRepository;
    uses io.tripled.social.client.domain.DateTimeProvider;
}