module persistence {
  exports io.tripled.social.client.infrastructure;

  provides io.tripled.social.client.domain.SocialNetworkRepository
      with io.tripled.social.client.infrastructure.InMemorySocialNetworkRepository;
  provides io.tripled.social.client.domain.DateTimeProvider
      with io.tripled.social.client.infrastructure.SystemDateTimeProvider;

  requires domain;
  requires hamcrest.core;
}